package Cliente.Modelos.Mensajes;

public class MensajeGeneral {
    private String type;
    private Object data;

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public MensajeGeneral(String type, Object data) {
        this.type = type;
        this.data = data;
    }
    public MensajeGeneral() {
    }

    @Override
    public String toString() {
        return "MensajeGeneral{" +
                "type='" + type + '\'' +
                ", data=" + data +
                '}';
    }
}

