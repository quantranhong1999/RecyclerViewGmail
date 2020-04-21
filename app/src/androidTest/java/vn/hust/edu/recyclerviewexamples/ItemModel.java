package vn.hust.edu.recyclerviewexamples;

public class ItemModel {
    String title;
    int imageResource;
    boolean isLeft;

    public ItemModel(String title, int imageResource) {
        this.title = title;
        this.imageResource = imageResource;
        this.isLeft = true;
    }

    public ItemModel(String title, int imageResource, boolean isLeft) {
        this.title = title;
        this.imageResource = imageResource;
        this.isLeft = isLeft;
    }

    public boolean isLeft() {
        return isLeft;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
}
