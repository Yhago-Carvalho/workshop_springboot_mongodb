package com.example.demo.resources;

import com.example.demo.domain.Post;
import com.example.demo.domain.User;
import com.example.demo.dto.UserDTO;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> list = userService.findAll();
        List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        Optional<User> user = userService.findById(id);
        UserDTO userDTO = new UserDTO(user.get());
        return ResponseEntity.ok().body(userDTO);
    }

    @PostMapping
    public ResponseEntity<UserDTO> insert(@RequestBody UserDTO objDto) {
        User obj = userService.fromDTO(objDto);
        userService.insert(obj);
        //pega o endereço do novo objeto inserido
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build(); //created retorna 201
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.noContent().build(); //noContent = 204 operação sem retorno
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody UserDTO objDto, @PathVariable String id) {
        User obj = userService.fromDTO(objDto);
        obj.setId(id);
        userService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}/posts")
    public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
        Optional<User> user = userService.findById(id);
        return ResponseEntity.ok().body(user.get().getPosts());
    }

}
