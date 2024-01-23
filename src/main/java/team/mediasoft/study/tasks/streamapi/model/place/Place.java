package team.mediasoft.study.tasks.streamapi.model.place;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Place {

    private String region;
    private PlaceType type;
    private String locality;
}
