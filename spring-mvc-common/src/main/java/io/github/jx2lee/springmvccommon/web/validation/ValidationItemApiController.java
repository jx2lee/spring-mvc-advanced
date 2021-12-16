package io.github.jx2lee.springmvccommon.web.validation;

import io.github.jx2lee.springmvccommon.web.form.ItemSaveForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/validation/api/items")
public class ValidationItemApiController {

    @PostMapping("/add")
    public Object addItem(@RequestBody
                          @Validated ItemSaveForm form,
                          BindingResult bindingResult) {

        log.info("컨트롤러 호출 성공");

        if (bindingResult.hasErrors()) {
            log.info("검증 오류 발생, errors={}", bindingResult);
            return bindingResult.getAllErrors();
        }

        log.info("정상 동작");
        return form;
    }
}
