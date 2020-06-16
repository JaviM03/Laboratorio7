package com.uca.capas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.uca.capas.dao.EstudianteDAO;
import com.uca.capas.domain.Estudiante;
import com.uca.capas.services.EstudianteService;


@Controller
public class MainController {
	@Autowired
	private EstudianteService estudianteService;
	
	@RequestMapping("/estudiante")
	public ModelAndView initMain() {
		ModelAndView mav=new ModelAndView();
		List<Estudiante> estudiantes= null;
		try {
			estudiantes = estudianteService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.addObject("estudiantes",estudiantes);
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping(value="/mostrarEstudiante",method=RequestMethod.POST)
	public ModelAndView findOne(@RequestParam(value="codigo") int id) {
		ModelAndView mav=new ModelAndView();
		Estudiante estudiante = estudianteService.findOne(id);
		mav.addObject("estudiante",estudiante);
		mav.setViewName("estudiante");
		return mav;
	}
	
	@PostMapping("/save")
	public ModelAndView guardar(@Valid @ModelAttribute Estudiante estudiante, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		if(result.hasErrors()) {
			mav.setViewName("agregarEstudiante");
		} else {
			estudianteService.save(estudiante);
			List<Estudiante> estudiantes =null;
			try {
				estudiantes=estudianteService.findAll();
			} catch(Exception e) {
				e.printStackTrace();
			}
			mav.addObject("estudiantes",estudiantes);
			mav.setViewName("listaEstudiantes");
	}
		return mav;
	}
	
	@RequestMapping(value="/busqueda", params="action=borrar")
	public ModelAndView delete(@RequestParam(value="codigo") int id) {
		ModelAndView mav = new ModelAndView();
		List<Estudiante> estudiantes = null;
		
		try {
			estudianteService.delete(id);
			estudiantes = estudianteService.findAll();
		}catch(Exception e) {
			e.printStackTrace();
		}
		mav.addObject("estudiantes", estudiantes);
		mav.setViewName("main");
		
		return mav;
	}
	@GetMapping("/insertarEstudiante")
	public ModelAndView inicio() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("estudiante", new Estudiante());
		mav.setViewName("agregarEstudiante");
		return mav;
	}
	
	@RequestMapping("/")
	public ModelAndView Main() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main");
		return mav;
	}
	
	@PostMapping("/filtrar")
	public ModelAndView filtrar(@RequestParam(value="nombre") String cadena) {
		ModelAndView mav = new ModelAndView();
		List<Estudiante> estudiantes=null;
		
			try {
				estudiantes=estudianteService.filtrarPor(cadena);
				//estudiantes=estudianteService.empiezaCon(cadena);
			} catch(Exception e) {
				e.printStackTrace();
			}
			mav.addObject("estudiantes",estudiantes);
			mav.setViewName("main");
		
		return mav;
	}
	
	@RequestMapping(value="/busqueda", params="action=actualizar")
	public ModelAndView update(@RequestParam(value="codigo") int id) {
		ModelAndView mav = new ModelAndView();
		Estudiante estudiante = estudianteService.findOne(id);
		mav.addObject("estudiante", estudiante);
		mav.setViewName("actualizarEstudiante");
		
		return mav;
	}
	
	@GetMapping("/actualizarEstudiante")
	public ModelAndView initEstu(@RequestParam(value="codigo") int id) {
		ModelAndView mav = new ModelAndView();
		Estudiante estudiante = estudianteService.findOne(id);
		mav.addObject("estudiante", new Estudiante());
		mav.setViewName("actualizarEstudiante");
		return mav;
	}
	@RequestMapping(value="/busqueda", params="action=buscar")
	public ModelAndView buscar(@RequestParam(value="codigo") int id) {
		ModelAndView mav = new ModelAndView();
		Estudiante estudiante = null;
		
		try {
			estudiante= estudianteService.findOne(id);
		}catch(Exception e) {
			e.printStackTrace();
		}
		mav.addObject("estudiante", estudiante);
		mav.setViewName("estudiante");
		
		return mav;
	}

}
