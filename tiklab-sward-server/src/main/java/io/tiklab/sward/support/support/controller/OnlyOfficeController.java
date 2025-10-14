package io.tiklab.sward.support.support.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.sward.support.support.model.OnlyOffice;
import io.tiklab.sward.support.support.model.OnlyOfficeQuery;
import io.tiklab.sward.support.support.service.OnlyOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/wiki/tool/onlyOffice")
public class OnlyOfficeController {


    @Autowired
    OnlyOfficeService onlyOfficeService;

    @RequestMapping(path="/createOnlyOffice",method = RequestMethod.POST)
    public Result<String> createOnlyOffice(@RequestBody @NotNull @Valid OnlyOffice onlyOffice){
        String id = onlyOfficeService.createOnlyOffice(onlyOffice);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateOnlyOffice",method = RequestMethod.POST)
    public Result<Void> updateOnlyOffice(@RequestBody @NotNull @Valid OnlyOffice onlyOffice){
        onlyOfficeService.updateOnlyOffice(onlyOffice);

        return Result.ok();
    }

    @RequestMapping(path="/deleteOnlyOffice",method = RequestMethod.POST)
     public Result<Void> deleteOnlyOffice(@NotNull String id){
        onlyOfficeService.deleteOnlyOffice(id);

        return Result.ok();
    }

    @RequestMapping(path="/findOnlyOffice",method = RequestMethod.POST)
    public Result<OnlyOffice> findOnlyOffice(@NotNull String id){
        OnlyOffice onlyOffice = onlyOfficeService.findOnlyOffice(id);

        return Result.ok(onlyOffice);
    }

    @RequestMapping(path="/findAllOnlyOffice",method = RequestMethod.POST)
    public Result<List<OnlyOffice>> findAllOnlyOffice(){
        List<OnlyOffice> onlyOfficeList = onlyOfficeService.findAllOnlyOffice();

        return Result.ok(onlyOfficeList);
    }

    @RequestMapping(path = "/findOnlyOfficeList",method = RequestMethod.POST)
    public Result<List<OnlyOffice>> findOnlyOfficeList(@RequestBody @Valid @NotNull OnlyOfficeQuery onlyOfficeQuery){
        List<OnlyOffice> onlyOfficeList = onlyOfficeService.findOnlyOfficeList(onlyOfficeQuery);

        return Result.ok(onlyOfficeList);
    }

    @RequestMapping(path = "/findOnlyOfficePage",method = RequestMethod.POST)
    public Result<Pagination<OnlyOffice>> findOnlyOfficePage(@RequestBody @Valid @NotNull OnlyOfficeQuery onlyOfficeQuery){
        Pagination<OnlyOffice> pagination = onlyOfficeService.findOnlyOfficePage(onlyOfficeQuery);

        return Result.ok(pagination);
    }
}
