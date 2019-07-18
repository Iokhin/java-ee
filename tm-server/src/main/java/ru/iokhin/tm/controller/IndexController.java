package ru.iokhin.tm.controller;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

import javax.faces.bean.ManagedBean;

@ManagedBean
@URLMapping(
        id = "welcome",
        pattern = "/welcome",
        viewId = "/WEB-INF/view/welcome.xhtml"
)
public class IndexController {
    public String welcome() {
        return "pretty:welcome";
    }
}
