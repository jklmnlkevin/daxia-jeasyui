package com.daxia.wy.web.controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daxia.core.common.Log;
import com.daxia.core.dto.JsonResultDTO;
import com.daxia.core.support.Page;
import com.daxia.core.web.controller.BaseController;
import com.daxia.wy.dto.ArticleDTO;
import com.daxia.wy.service.ArticleService;
import com.google.common.collect.Lists;

/**
 * 以Admin开头的Controller，都是只为管理后台提供服务的
 */
// @Module(name = "图文模块")
@Controller
@RequestMapping(value = "/admin/article", produces="text/html;charset=UTF-8") // produces是为了解决@ResponseBody返回乱码
public class AdminArticleController extends BaseController {

	/**
	 * @Autowired 表示自动注入spring管理的bean，不需要写getter和setter就能注入
	 */
	@Autowired
	private ArticleService articleService;
	
	/**
	 * 详情页面。用来获得单个对象的具体信息的
	 * map是用来传值 ，在页面通过${key}的方式就能得到值 。比如map里put("article", dto)，在页面取就是${article.id}这种。
	 * @param map
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/detail")
	public String detail(Map<String, Object> map, Long id) {
		if (id != null) {
			ArticleDTO dto = articleService.load(id);
			map.put("article", dto);
		}
		return "admin/article/article_detail";
	}
	
	/**
	 * 如果dto.getId不为空，则为更新，如果为空，则是保存.
	 * @ResponseBody 表示这是一个接收json请求的方式，返回值 不会跳转到哪个页面，返回值为void。
	 * 要传给页面的json值，请调用父类BaseController提供的三个方法。
	 * @param dto
	 */
	@Log(operation = "保存图文") 
	@ResponseBody
	@RequestMapping(value = "/save")
	@PreAuthorize("(hasRole('article.update') and #dto.id != null) or (hasRole('article.add') and #dto.id == null)")
	public String save(HttpServletRequest request, ArticleDTO dto, JsonResultDTO resultDTO) throws Exception {
		if (dto.getId() == null) {
			Long id = articleService.create(dto);
			ArticleDTO model = articleService.load(id);
			model.setUrl(request.getSession().getAttribute("ctx").toString() + "/article/" + id);
			articleService.updateAllFields(model);
		} else {
			articleService.updateAllFields(dto);
		}
		return okAndClose(resultDTO);
	}

    @Log(operation = "删除图文")
    @PreAuthorize("hasRole('article.delete')")
	@ResponseBody
	@RequestMapping(value = "/delete") 
	public String delete(Long[] ids, JsonResultDTO resultDTO) throws IOException {
		articleService.deleteByIds(ids);
		return okAndRefresh(resultDTO);
	}
	
	@RequestMapping(value = "/list")
	@PreAuthorize("hasRole('article.list')")
	public String list(ArticleDTO dto, Map<String, Object> map, Page page) {
		List<ArticleDTO> dtos = articleService.find(dto, page);
		// 这个数据是用来展示的
		map.put("articles", dtos);
		// 这个数据是保存查询条件的
		map.put("article", dto);
		return "admin/article/article_list";
	}
	
    @RequestMapping("imageLink")
    public String imageLink(Long id, Map<String, Object> map) throws Exception {
    	ArticleDTO dto = articleService.load(id);
    	map.put("urls", extractUrls(dto.getContent()));
    	return "admin/article/article_imageLink";
    }
    
    private List<String> extractUrls(String content) {
    	List<String> urls = Lists.newArrayList();
	    Pattern pattern = Pattern.compile("<img .*?/>");
	    Matcher matcher = pattern.matcher(content);
	    while (matcher.find()) {
	    	String group = matcher.group();
	    	String src = group.split("src=\"")[1].split("\"")[0];
	    	urls.add(src);
	    }
	    return urls;
    }
    
}
