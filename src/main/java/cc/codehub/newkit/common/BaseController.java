package cc.codehub.newkit.common;

import cc.codehub.newkit.base.ErrorResponse;
import cc.codehub.newkit.base.Response;
import org.springframework.validation.BindingResult;


public abstract class BaseController {
    protected Response response;

    protected boolean baseValidation(BindingResult result){
        if(result.hasErrors()){
            response = new ErrorResponse(ApiError.INVALID_PARAMETERS.getCode(), result.getFieldError().getDefaultMessage());
        }
        return result.hasErrors();
    }

    protected void databaseOperationError(Exception e){
        response = new ErrorResponse(ApiError.DATABASE_OPERATION_FAIL.getCode(), e.getMessage());
    }

}
