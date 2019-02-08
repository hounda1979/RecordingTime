package tw.hd.com.recodtime;

public abstract class RecordData {
    int ch;
    int stream;

    public RecordData(int ch, int stream) {
        this.ch = ch;
        this.stream = stream;
    }

    public abstract String calculation();

}
