package cc.codehub.newkit.base;


public class SuccessResponse extends Response {

    private SuccessResponse() {

    }

    public SuccessResponse(Object obj) {
        this.addAttribute("result", "success");
        this.addAttribute("data", obj);
    }

    public String getResult() {
        return (String)this.getAttribute("result");
    }

    public Object getData() {
        return this.getAttribute("data");
    }

    public void setData(Object obj) {
        this.addAttribute("data", obj);
    }
}
