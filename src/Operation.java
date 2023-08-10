public enum Operation {
    ADDITION("+"),
    SUBTRACTION("-"),
    MULTIPLICTION("*"),
    DIVISION("/");
    private String title;
    Operation(String s) {
        title = s;
    }
    public String getTitle(){
        return title;
    }
}
