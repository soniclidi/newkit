package cc.codehub.newkit.base;


public class ErrorResponse extends Response {

    private ErrorResponse() {

    }

    public ErrorResponse(String code, String desc) {
        this.addAttribute("result", "error");
        this.addAttribute("code", code);
        this.addAttribute("desc", desc);
    }

    public ErrorResponse(Error err) {
        this.addAttribute("result", "error");
        this.addAttribute("code", err.getCode());
        this.addAttribute("desc", err.getMessage());
    }

    public String getResult() {
        return (String)this.getAttribute("result");
    }

    public String getCode() {
        return (String)this.getAttribute("code");
    }

    public void setCode(String code) {
        this.addAttribute("code", code);
    }

    public String getDesc() {
        return (String)this.getAttribute("desc");
    }

    public void setDesc(String desc) {
        this.addAttribute("desc", desc);
    }
}
