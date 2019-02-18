package com.pf.sql.editor.controller;

import com.pf.core.entity.Result;
import com.pf.spring.base.BaseController;
import com.pf.sql.editor.service.SqlCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Author: Ru He
 * Date: Created in 2019/1/30.
 * Description:
 */
@RestController
@RequestMapping("/editor")
public class SqlEditorController extends BaseController{

    @Autowired
    private SqlCommandService commandService;

    @RequestMapping(value = "/action", method = RequestMethod.POST)
    public Result action(@RequestBody Map<String, Object> params){
        Result result = new Result();
        validateEmpty(params, "query");
        result.setData(commandService.action(params));
        return result;
    }


}
