package Adapter.JSON_and_XML;

public class JSONAdapter implements  ClientDataReader{
    private JSONReader jsonReader;

    public JSONAdapter(JSONReader jsonReader) {
        this.jsonReader = jsonReader;
    }


    @Override
    public void readClientData() {
        jsonReader.fetchJSON();
    }
}
