package responsePojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetUsersResponse {

    int page;
    int per_page;
    int total;
    int total_pages;
    List<UserData> data;
}
