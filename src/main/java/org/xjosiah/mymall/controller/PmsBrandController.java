package org.xjosiah.mymall.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.xjosiah.mymall.common.api.CommonPage;
import org.xjosiah.mymall.common.api.CommonResult;
import org.xjosiah.mymall.mbg.model.PmsBrand;
import org.xjosiah.mymall.service.PmsBrandService;

import java.util.List;


@Controller
@RequestMapping("/brand")
public class PmsBrandController {

    @Autowired
    PmsBrandService pmsBrandService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);

    @GetMapping("/listAll")
    @ResponseBody
    public CommonResult<List<PmsBrand>> getBrandList(){
        return CommonResult.success(pmsBrandService.listAllBrand());
    }

    @PostMapping("/create")
    @ResponseBody
    public CommonResult createBrand(@RequestBody PmsBrand pmsBrand){
        CommonResult commonResult;
        int count = pmsBrandService.createBrand(pmsBrand);
        if (count==1) {
            commonResult = CommonResult.success(pmsBrand);
            LOGGER.debug("createBrand success:{}",pmsBrand);
        }else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("createBrand failed:{}",pmsBrand);
        }
        return commonResult;
    }

    @PostMapping("/update/{id}")
    @ResponseBody
    public CommonResult updateBrand(@PathVariable("id") Long id, @RequestBody PmsBrand pmsBrandDto, BindingResult result){
        CommonResult commonResult;
        int count = pmsBrandService.updateBrand(id, pmsBrandDto);
        if (count==1) {
            commonResult = CommonResult.success(pmsBrandDto);
            LOGGER.debug("createBrand success:{}",pmsBrandDto);
        }else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("createBrand failed:{}",pmsBrandDto);
        }
        return commonResult;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult deleteBrand(@PathVariable("id") Long id) {
        int count = pmsBrandService.deleteBrand(id);
        if (count == 1) {
            LOGGER.debug("deleteBrand success :id={}", id);
            return CommonResult.success(null);
        } else {
            LOGGER.debug("deleteBrand failed :id={}", id);
            return CommonResult.failed("操作失败");
        }
    }

    @GetMapping("/list")
    @ResponseBody
    public CommonResult<CommonPage<PmsBrand>> listBrand(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                        @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) {
        List<PmsBrand> brandList = pmsBrandService.listBrand(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(brandList));
    }

    @GetMapping("/{id}")
    @ResponseBody
    public CommonResult<PmsBrand> brand(@PathVariable("id") Long id) {
        return CommonResult.success(pmsBrandService.getBrand(id));
    }
}
