import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) {
        List<DataJoined> dataJoineds = getSampleData();

        final List<DataReduced> dataReducedList = dataJoineds.stream().map(dataJoined -> {
            final Data imageData = dataJoined.getImage();
            final long imageTimestamp = imageData.getTimestamp().getTime();

            final Data gpsData = dataJoined.getGpsData().stream().reduce((data, data2) ->
                    Math.abs(imageTimestamp - data.getTimestamp().getTime())
                            < Math.abs(imageTimestamp - data2.getTimestamp().getTime())
                            ? data : data2).orElse(null);

            return DataReduced.builder().image(imageData).gpsData(gpsData).build();
        }).collect(Collectors.toList());


        System.out.println(dataReducedList);
    }

    private static List<DataJoined> getSampleData() {
        final long epoch = System.currentTimeMillis();

        List<Data> gpsData = new ArrayList<Data>() {{
            add(Data.builder().id("1").timestamp(new Date(epoch - 19)).build());
            add(Data.builder().id("2").timestamp(new Date(epoch - 8)).build());
            add(Data.builder().id("3").timestamp(new Date(epoch + 2)).build());
            add(Data.builder().id("4").timestamp(new Date(epoch + 11)).build());
            add(Data.builder().id("5").timestamp(new Date(epoch + 19)).build());
        }};

        return Collections.singletonList(DataJoined.builder()
                .image(Data.builder().id("1").timestamp(new Date(epoch)).build())
                .gpsData(gpsData).build());
    }
}
