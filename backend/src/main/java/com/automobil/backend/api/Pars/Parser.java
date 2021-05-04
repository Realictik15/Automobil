package com.automobil.backend.api.Pars;

import com.automobil.backend.dto.AccidentsApi;
import com.automobil.backend.dto.ReportDto;
import com.automobil.backend.dto.RestrictoinApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Component
public class Parser {
    private final JsonParser parser = new JsonParser();

    public ReportDto parseGibdd(String json) {
        ReportDto reportDto = new ReportDto();
        AccidentsApi accidentsApi;
        RestrictoinApi restrictoinApi;
        List<RestrictoinApi> restrictoinApis = new ArrayList<>();
        List<AccidentsApi> accidentsApiList = new ArrayList<>();
        JsonElement jsonTree = parser.parse(json);
        if (jsonTree.isJsonObject()) {
            JsonObject jsonObject = jsonTree.getAsJsonObject();
            JsonElement historylist = jsonObject.get("history");
            if (historylist.isJsonObject()) {
                JsonObject history = historylist.getAsJsonObject();
                reportDto.setEngineNumber(extractString(history, "engineNumber"));
                reportDto.setBuyYear(extractInt(history, "year"));
                List<JsonObject> ownersList = extractList(history, "ownershipPeriods");
                reportDto.setCountOwners(ownersList.size());
                reportDto.setLastOwnersDate(extractString(ownersList.get(ownersList.size() - 1), "from"));
            }
            List<JsonObject> accidentslist = extractList(jsonObject, "accidents");
            reportDto.setCountAccidents(accidentslist.size());
            if (accidentslist.size() > 0) {
                for (JsonObject j : accidentslist) {
                    if (j.isJsonObject()) {
                        accidentsApi = new AccidentsApi();
                        accidentsApi.setYearAccident(extractString(j, "accidentDatetime"));
                        accidentsApi.setAccidentType(extractString(j, "accidentType"));
                        accidentsApi.setPlace(extractString(j, "accidentPlace"));
                        accidentsApi.setDamageDescription(extractString(j, "damageDescription"));
                        accidentsApi.setUrlImages(extractString(j, "damageImages"));
                        accidentsApiList.add(accidentsApi);
                    }
                }
                reportDto.setAccidents(accidentsApiList);
            }

            List<JsonObject> restrictionlist = extractList(jsonObject, "restrictions");
            if (restrictionlist.size() > 0) {
                for (JsonObject j : restrictionlist) {
                    if (j.isJsonObject()) {
                        restrictoinApi = new RestrictoinApi();
                        restrictoinApi.setYearRestrict(extractString(j, "restriction_date"));
                        restrictoinApi.setCity(extractString(j, "region"));
                        restrictoinApi.setOrganization(extractString(j, "organization_name"));
                        restrictoinApi.setTypeRestrict(extractString(j, "restriction_name"));
                        restrictoinApi.setReasons(extractString(j, "reasons"));
                        restrictoinApis.add(restrictoinApi);
                    }
                }
                reportDto.setRestrictoins(restrictoinApis);
            }
            List<JsonObject> diagnostionlist = extractList(jsonObject, "diagnosticCards");
            if (diagnostionlist.size() > 0) {
                JsonObject diagnost = diagnostionlist.get(diagnostionlist.size() - 1);
                reportDto.setMileage(extractInt(diagnost, "odometerValue"));
                reportDto.setLastDiagnostic(extractString(diagnost, "dcExpirationDate"));
            }
        }
        return reportDto;
    }


    public String extractString(JsonObject json, String name) {
        if (!json.has(name))
            return "null";
        JsonElement element = json.get(name);
        if (element.isJsonNull())
            return "null";
        return element.getAsString();
    }

    public Integer extractInt(JsonObject json, String name) {
        if (!json.has(name))
            return -1;
        JsonElement element = json.get(name);
        if (element.isJsonNull())
            return -1;
        return element.getAsInt();
    }

    public List<JsonObject> extractList(JsonObject json, String name) {
        if (!json.has(name))
            return new ArrayList<>();
        JsonElement element = json.get(name);
        if (element.isJsonNull())
            return new ArrayList<>();

        Type listType = new TypeToken<List<JsonObject>>() {
        }.getType();
        return new Gson().fromJson(element, listType);
    }
}
