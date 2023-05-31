package com.news.amaris.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.news.amaris.entities.AmarisNewsEntity;
import com.news.amaris.exceptions.AmarisNewsException;
import com.news.amaris.services.AmarisNewsBookmarksService;
import com.news.amaris.services.AmarisNewsService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/api")
public class AmarisNewsController {
	
	@Autowired
	private AmarisNewsService amarisNewsService;
	
	@Autowired
	private AmarisNewsBookmarksService bookmarksService;

	@ApiOperation(value = "Valida estado del componente", notes = "Retorna un string con el estado del servicio")
	@ApiResponses(value = {
			  @ApiResponse(code = 200, message = "El servicio se encuentra activo"),
			})
	@GetMapping("healthcheck")
	public ResponseEntity<String> healthcheck(){
		return new ResponseEntity<>("Status Success", HttpStatus.OK);
	}
	
	@ApiOperation(value = "Consultar la lista de las ultimas noticias", notes = "Retorna una lista de noticias")
	@ApiResponses(value = {
			  @ApiResponse(code = 200, message = "Se ha ejecutado con exito la consulta"),
			})
	@GetMapping(value = "/news", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Object> getAll() {
        try {
			return new ResponseEntity<>(amarisNewsService.getAll(), HttpStatus.OK);
		} catch (AmarisNewsException e) {
			return new ResponseEntity<>(e.getError(), e.getError().getHttpStatus());
		}
    }
	
	@ApiOperation(value = "Consultar la lista de las noticias favoritas", notes = "Retorna una lista de noticias favoritas")
	@ApiResponses(value = {
			  @ApiResponse(code = 200, message = "Se ha ejecutado con exito la consulta"),
			})
	@GetMapping(value = "/bookmarks", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<AmarisNewsEntity>> getAllBookmark() {
        return new ResponseEntity<>(bookmarksService.getAll(), HttpStatus.OK);
    }
	
	@ApiOperation(value = "Quitar noticia de la lista de favoritos", notes = "Retorna mensaje con el resultado de la operacion")
	@ApiResponses(value = {
			  @ApiResponse(code = 200, message = "Se ha eliminado la noticia de la lista de favoritos"),
			  @ApiResponse(code = 500, message = "Error al eliminar la noticia de la lista de favoritos")
			})
	@DeleteMapping(value = "/news/bookmark/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Object> deleteBookmark(@PathVariable("id") Long id) {
		
		try {
			if(amarisNewsService.removeBookmarkNews(id)) {
				return new ResponseEntity<>("{\"response\": \"The bookmark news has been deleted\"}", HttpStatus.OK);
			}else {
				return new ResponseEntity<>("{\"response\": \"The bookmark news can't deleted\"}", HttpStatus.OK);
			}
		} catch (AmarisNewsException e) {
			return new ResponseEntity<>(e.getError(), e.getError().getHttpStatus());
		}
		
        
    }
	
	@ApiResponses(value = {
			  @ApiResponse(code = 200, message = "Se ha eliminado la noticia de la lista de favoritos"),
			  @ApiResponse(code = 500, message = "Error al eliminar la noticia de la lista de favoritos")
			})
	@PostMapping(value = "/news/bookmark/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<Object> addBookmarkById(@PathVariable("id") Long id) {
		
		try {
			if(amarisNewsService.addBookmarkNews(id)) {
				return new ResponseEntity<>("{\"response\": \"The bookmark news has been added\"}", HttpStatus.OK);
			}else {
				return new ResponseEntity<>("{\"response\": \"The bookmark news can't added\"}", HttpStatus.OK);
			}
		} catch (AmarisNewsException e) {
			return new ResponseEntity<>(e.getError(), e.getError().getHttpStatus());
		}
		
		
        
    }
}
