package inventorys.models;

public class Items {
    private int trackId;
    private String productName;
    private float quantity;
    private String storedSection;
    private String  dateOfExpired;
    private String importDate;

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public String getStoredSection() {
        return storedSection;
    }

    public void setStoredSection(String storedSection) {
        this.storedSection = storedSection;
    }

    public String getDateOfExpired() {
        return dateOfExpired;
    }

    public void setDateOfExpired(String dateOfExpired) {
        this.dateOfExpired = dateOfExpired;
    }

    public String getImportDate() {
        return importDate;
    }

    public void setImportDate(String importDate) {
        this.importDate = importDate;
    }
}
