public enum statusCustomer {

    PENDING_CHECK_IN("Pending Check In"),
    CHECKED_IN("Checked In"),
    CHECKED_OUT("Checked Out");

    // Fields
    public String status;

    // Ctor
    private statusCustomer(String status) {
        this.status = status;
    }

    // Methods
    @Override
    public String toString() {
        return this.status;
    }
}