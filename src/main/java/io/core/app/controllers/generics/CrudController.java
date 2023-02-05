package io.core.app.controllers.generics;



import io.core.app.errors.ValidationErrorBuilder;
import io.core.app.exceptions.ResourceNotFoundException;
import io.core.app.services.generics.CrudServices;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@Secured({
        "ROLE_ADMIN",
        "ROLE_USER"
})
@Data
public abstract class CrudController<T> {

    protected CrudServices service;

    public CrudController(){
        super();

    }
    public CrudController(CrudServices service)
    {
        super();
        this.service=service;
    }

//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "Authorization", value = "Authorization ", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
//    })
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> queryByPage(Pageable pageable) {

        Page<T> pageInfo = service.findAll(pageable);
        if (pageInfo.getContent().isEmpty()) {
            return new ResponseEntity<Page<T>>(pageInfo, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Page<T>>(pageInfo, HttpStatus.OK);
    }

//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "Authorization", value = "Authorization ", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
    //})
    @DeleteMapping(value = "{id}/")
    public void delete(@PathVariable long id) {
        service.deleteById(id);
    }

//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "Authorization", value = "Authorization ", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
//    })
    @PatchMapping("{id}/")
    public ResponseEntity<?> updateByIdAndElement(@RequestParam("id") long id, @Valid @RequestBody T element, Errors errors) throws Throwable {

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }

        this.service.findById(id).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + id));


        Optional<T> optionalProduct = this.service.UpdateById(id, element);
        if (optionalProduct.isPresent()) {
            return new ResponseEntity<T>(optionalProduct.get(), HttpStatus.OK);
        }
        return new ResponseEntity<String>("Without updates", HttpStatus.NO_CONTENT);
    }


//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "Authorization", value = "Authorization ", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
//    })
    @PutMapping("{id}/")
    public ResponseEntity<?> updateEmployee(@PathVariable("id") long id, @Valid @RequestBody T elementForUpdate, Errors errors) throws Throwable {

        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }
        this.service.findById(id).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + id));

        Optional<T> optionalProduct = service.UpdateById(id, elementForUpdate);
        if (optionalProduct.isPresent()) {
            return new ResponseEntity<T>(optionalProduct.get(), HttpStatus.OK);
        }
        return new ResponseEntity<String>("Without updates", HttpStatus.NO_CONTENT);
    }

//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "Authorization", value = "Authorization ", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer ")
//    })
    @PostMapping
    public ResponseEntity<?> creatProduct(@Valid @RequestBody T element, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(ValidationErrorBuilder.fromBindingErrors(errors));
        }
        return new ResponseEntity<T>((T) service.create(element), HttpStatus.OK);

    }


}
