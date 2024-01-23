package kr.or.ddit.property.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import kr.or.ddit.property.service.PropertyService;
import kr.or.ddit.vo.PropertyVO;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

@Controller
@RequestMapping({"/09/property", "/09/property/*"})
public class PropertiesController {

	@Inject
	private PropertyService service;

    @GetMapping
    public ResponseEntity<Object> list() {
        Function<PropertyVO, String> resolve = PropertyVO::getPropertyName;
        Set<String> keySet = service.retrieveKeySet();
        return ResponseEntity.ok().body(keySet);
    }

    @GetMapping("/propertyName")
    public ResponseEntity<Object> single(@PathVariable String propertyName) {
        PropertyVO property = service.retrieveProperty(propertyName);
        if (property == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.ok().body(property);
        }
    }

    private String getPropertyName(String requestURI) throws UnsupportedEncodingException {
        String propertyName = null;

        String regex = "\\S*/09/property/(\\S+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(requestURI);

        if (matcher.find()) {
            propertyName = URLDecoder.decode(matcher.group(1), "UTF-8");
        }

        return propertyName;
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody PropertyVO newProp) throws IOException {
        boolean success = service.createProperty(newProp);
        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody PropertyVO modifyProp) throws IOException {
        boolean success = service.updateProperty(modifyProp);
        return ResponseEntity.status(success ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @DeleteMapping("/{propertyName}")
    public ResponseEntity<Object> delete(@PathVariable String propertyName) {
        boolean success = service.deleteProperty(propertyName);
        return ResponseEntity.status(success ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}