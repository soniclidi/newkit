package cc.codehub.newkit.controller;

import cc.codehub.newkit.base.ErrorResponse;
import cc.codehub.newkit.base.Response;
import cc.codehub.newkit.base.SuccessResponse;
import cc.codehub.newkit.common.ApiError;
import cc.codehub.newkit.common.BaseController;
import cc.codehub.newkit.model.User;
import cc.codehub.newkit.service.AccessAndRoleService;
import cc.codehub.newkit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RequestMapping("user")
@RestController
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private AccessAndRoleService accessAndRoleService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Response addUser(@RequestBody User user) {
        try {
            response = new SuccessResponse(userService.createUser(user));
        } catch (Exception e) {
            response = new ErrorResponse(ApiError.DATABASE_OPERATION_FAIL.getCode(), e.getMessage());
        }

        return response;
    }

    @RequestMapping(value = "get/{id}", method = RequestMethod.POST)
    public Response getUser(@PathVariable int id) {
        try {
            response = new SuccessResponse(userService.getUserById(id));
        } catch (Exception e) {
            response = new ErrorResponse(ApiError.DATABASE_OPERATION_FAIL.getCode(), e.getMessage());
        }

        return response;
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
    @Transactional
    public Response deleteUser(@PathVariable int id) {
        try {
            accessAndRoleService.deleteRoleUserMapByUserId(id);
            userService.deleteUser(id);
            response = new SuccessResponse(null);
        } catch (Exception e) {
            response = new ErrorResponse(ApiError.DATABASE_OPERATION_FAIL.getCode(), e.getMessage());
        }

        return response;
    }

}
