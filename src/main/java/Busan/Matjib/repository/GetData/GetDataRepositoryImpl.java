package Busan.Matjib.repository.GetData;

import Busan.Matjib.dto.Getdata.MatJibDto;
import Busan.Matjib.dto.Getdata.TravelDto;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GetDataRepositoryImpl implements GetDataRepository{

    @Override
    public List<MatJibDto> GetFoodData() throws Exception {
        List<MatJibDto> foodLi = new ArrayList<>();

        String url  = "http://apis.data.go.kr/6260000/FoodService/getFoodKr"; /*URL*/
        String serviceKey = "=jrLHt4JyJ5Ekiiok06JukET40YfK9YrFUNt%2FwVvLdBIt7sZaGbyU%2F9rjGuk0WYaW%2FD5160RFRzdvD7JqpalbKg%3D%3D"; /*Service Key (일반인증키)*/
        String pageNum = "1"; /*페이지 번호*/
        String numOfRows = "150"; /*한 페이지 결과 수 (최소 10, 최대 9999)*/
        String returnType = "xml"; /*상태갱신 조회 범위(분) (기본값 5, 최소 1, 최대 10)*/

        StringBuilder sb = ConnectionURL(url, serviceKey, pageNum, numOfRows, returnType);

        Element[] elements = ConnectionData(sb);
        int size = elements[1].getElementsByTagName("item").getLength();

        for(int i=0 ; i<size; i++ ) {
            Element item =(Element) elements[1].getElementsByTagName("item").item(i);
            Node[]data = {
                    GetData(item, "MAIN_TITLE"),  // 필요한 데이터 값 추출하기
                    GetData(item, "LNG"),
                    GetData(item, "LAT"),
                    GetData(item, "CNTCT_TEL"),
                    GetData(item, "ITEMCNTNTS"),
                    GetData(item, "USAGE_DAY_WEEK_AND_TIME"),
                    GetData(item, "GUGUN_NM"),
                    GetData(item, "ADDR1"),
                    GetData(item, "RPRSNTV_MENU"),
                    GetData(item, "MAIN_IMG_THUMB"),
            };

            String[] strData= new String[data.length];

            for(int a=0; a<data.length;a++) {
                strData[a] = (String) PutData(data[a]);
            }

            MatJibDto food = new MatJibDto(
                    strData[0], Double.parseDouble(strData[1]), Double.parseDouble(strData[2]), strData[3],
                    strData[4], strData[5], strData[6], strData[7],
                    strData[8], strData[9]
            );

            foodLi.add(food);
        }
        return foodLi;
    }

    @Override
    public List<TravelDto> GetTravelData() throws Exception {
        List<TravelDto> travelLi = new ArrayList<>();

        String url = "http://apis.data.go.kr/6260000/AttractionService/getAttractionKr"; /*URL*/
        String serviceKey = "=jrLHt4JyJ5Ekiiok06JukET40YfK9YrFUNt%2FwVvLdBIt7sZaGbyU%2F9rjGuk0WYaW%2FD5160RFRzdvD7JqpalbKg%3D%3D"; /*Service Key (일반인증키)*/
        String pageNum = "1"; /*페이지 번호*/
        String numOfRows = "125"; /*한 페이지 결과 수 (최소 10, 최대 9999)*/
        String returnType = "xml"; /*상태갱신 조회 범위(분) (기본값 5, 최소 1, 최대 10)*/

        StringBuilder sb = ConnectionURL(url, serviceKey, pageNum, numOfRows, returnType);

        Element[] elements = ConnectionData(sb);
        int size = elements[1].getElementsByTagName("item").getLength();


        for(int i=0 ; i<size; i++ ) {
            Element item =(Element) elements[1].getElementsByTagName("item").item(i);

            Node []data = {
                    GetData(item,"MAIN_TITLE"),
                    GetData(item,"TITLE"),
                    GetData(item,"LNG"),
                    GetData(item,"LAT"),
                    GetData(item,"MIDDLE_SIZE_RM1"),
                    GetData(item,"USAGE_AMOUNT"),
                    GetData(item,"CNTCT_TEL"),
                    GetData(item,"TRFC_INFO"),
                    GetData(item,"HLDY_INFO"),
                    GetData(item,"SUBTITLE"),
                    GetData(item,"ITEMCNTNTS"),
                    GetData(item,"USAGE_DAY/"),
                    GetData(item,"USAGE_DAY_WEEK_AND_TIME"),
                    GetData(item,"GUGUN_NM"),
                    GetData(item,"ADDR1"),
                    GetData(item,"HOMEPAGE_URL"),
                    GetData(item,"MAIN_IMG_THUMB")
            };

            String[] strData= new String[data.length];

            for(int a=0; a<data.length;a++) {
                strData[a] = PutData(data[a]);
            }

            TravelDto travel = new TravelDto(
                    strData[0], strData[1], Double.parseDouble(strData[2]), Double.parseDouble(strData[3]),
                    strData[4], strData[5], strData[6], strData[7],
                    strData[8], strData[9], strData[10], strData[12],
                    strData[13], strData[14], strData[15], strData[16]
            );
            travelLi.add(travel);

        }
        return travelLi;
    }

    private StringBuilder ConnectionURL(String urlAddr, String serviceKey, String pageNo, String numOfRows, String returnType) throws Exception {
        StringBuilder urlBuilder = new StringBuilder(urlAddr); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + serviceKey); /*Service Key (일반인증키)*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8")); /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode(numOfRows, "UTF-8")); /*한 페이지 결과 수 (최소 10, 최대 9999)*/
        urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode(returnType, "UTF-8")); /*상태갱신 조회 범위(분) (기본값 5, 최소 1, 최대 10)*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        return sb;
    }

    private Element[] ConnectionData(StringBuilder sb) throws Exception {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder=dbFactory.newDocumentBuilder();

        FileOutputStream output = new FileOutputStream("./ApiExplorer");
        output.write(sb.toString().getBytes("UTF-8"));  // 전체 데이터 읽어 오기
        output.close();

        Document doc = dBuilder.parse("./ApiExplorer");
        doc.getDocumentElement().normalize();

        Element body =(Element) doc.getElementsByTagName("body").item(0);
        Element items =(Element) body.getElementsByTagName("items").item(0);

        Element[] elements = {body, items};

        return elements;
    }


    private Node GetData(Element item, String tagName){
        return item.getElementsByTagName(tagName).item(0);
    }
    private String PutData(Node data){
        try {
            return data.getChildNodes().item(0).getNodeValue();
        }catch(NullPointerException e) {
            return "";
        }
    }
}
