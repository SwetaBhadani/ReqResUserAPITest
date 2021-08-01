package utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.util.Map;

public class RequestHelper {

    ObjectMapper objectMapper;

    public RequestHelper() {
        this.objectMapper = new ObjectMapper();
    }

    public String getCreateUserAPIRequest(Map<String, String> map) {
        JSONObject jsonObject = new JSONObject();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            jsonObject.put(entry.getKey(), entry.getValue());
        }

        return jsonObject.toString();
    }

    public String getCreateUserAPIInvalidRequest() {
        return "{\"\"}";
    }
}
