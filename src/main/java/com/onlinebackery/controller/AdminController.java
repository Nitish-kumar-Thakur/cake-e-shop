package com.onlinebackery.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.onlinebackery.dto.ProductDto;
import com.onlinebackery.entity.Category;
import com.onlinebackery.entity.Product;
import com.onlinebackery.service.CategoryService;
import com.onlinebackery.service.ProductService;

@Controller
public class AdminController {
	private static String UploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";

	@Autowired
	private CategoryService cService;
	@Autowired
	private ProductService pService;

@RequestMapping("/admin")
public String getAdminHome(){
	return "adminHome";
}
	// Category card -------------
	@GetMapping("/admin/categories")

	public ModelAndView getCatogiries() {
		List<Category> list = cService.getAll();
		return new ModelAndView("categories", "categories", list);
	}


	@GetMapping("/admin/categories/add")
	public ModelAndView getAddCatogories() {
		return new ModelAndView("categoriesAdd", "category", new Category());
	}

	@PostMapping("/admin/categories/add")
	public String setAddCatogories(@ModelAttribute Category cat) {
		cService.insert(cat);
		return "redirect:/admin/categories";
	}

	@GetMapping("/admin/categories/delete/{id}")
	public String getDeleteCategories(@PathVariable("id") int id) {
		cService.delete(id);
		return "redirect:/admin/categories";
	}

	@GetMapping("/admin/categories/update/{id}")
	public ModelAndView getUpdateCategories(@PathVariable("id") int id) {
		Category cat = cService.getCategoryById(id);
		return new ModelAndView("categoriesAdd", "category", cat);
	}
	// --------

	// product Card-----
	@GetMapping("/admin/products")
	public ModelAndView getProduct() {
		List<Product> list = pService.getAll();

		return new ModelAndView("products", "products", list);
	}

	@GetMapping("/admin/products/add")
	public String getProductAdd(Model model) {
		model.addAttribute("productDTO", new ProductDto());
		model.addAttribute("categories", cService.getAll());
		return "productsAdd";
	}

	@PostMapping("/admin/products/add")
	public String setAddProduct(@ModelAttribute ProductDto productDto , @RequestParam("productImage") MultipartFile file,
			@RequestParam("imgName") String imgName) throws IOException{
		Product prod= new Product();
		prod.setId(productDto.getId());
		prod.setName(productDto.getName());
		prod.setPrice(productDto.getPrice());
		prod.setCategory(cService.getCategoryById(productDto.getCategoryId()));
		prod.setWeight(productDto.getWeight());
		prod.setDescription(productDto.getDescription());
		String imgUUId;
		if(!file.isEmpty()) {
			imgUUId=file.getOriginalFilename();
			Path pathAndName = Paths.get(UploadDir,imgUUId);
			Files.write(pathAndName, file.getBytes());
		}else {
			imgUUId = imgName;
		}
		prod.setImageName(imgUUId);
		pService.insert(prod);
		return "redirect:/admin/products";
	}

	@GetMapping("/admin/product/delete/{id}")
	public String getDeleteProduct(@PathVariable("id") Long id) {
		pService.delete(id);
		return "redirect:/admin/products";
	}

	@GetMapping("/admin/product/update/{id}")
	public String getUpdateProduct(@PathVariable("id") Long id, Model model) {
		Product product = pService.getProductById(id);
		ProductDto prod= new ProductDto();
		prod.setId(product.getId());
		prod.setName(product.getName());
		prod.setPrice(product.getPrice());
		prod.setCategoryId(product.getCategory().getId());
		prod.setWeight(product.getWeight());
		prod.setDescription(product.getDescription());
		prod.setImageName(product.getImageName());
		
		model.addAttribute("productDTO", prod);
		model.addAttribute("categories", cService.getAll());
		return "productsAdd";
	}
}
