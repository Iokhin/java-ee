package ru.iokhin.tm.faces;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

import javax.faces.bean.ManagedBean;

@ManagedBean
@URLMapping(
        id = "welcome",
        pattern = "/welcome",
        viewId = "/WEB-INF/view/welcome.xhtml"
)
public class IndexController {
}
