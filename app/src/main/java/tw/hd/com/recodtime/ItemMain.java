package tw.hd.com.recodtime;

public class ItemMain {
    int iconImage;
    String iconName;

    public ItemMain(int iconImage, String iconName) {
        this.iconImage = iconImage;
        this.iconName = iconName;
    }

    public int getIconImage() {
        return iconImage;
    }

    public void setIconImage(int iconImage) {
        this.iconImage = iconImage;
    }

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }
}
