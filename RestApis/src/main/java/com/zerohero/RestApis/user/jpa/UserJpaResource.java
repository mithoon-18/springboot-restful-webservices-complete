package com.zerohero.RestApis.user.jpa;



import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.zerohero.RestApis.user.Post;
import com.zerohero.RestApis.user.User;
import com.zerohero.RestApis.user.UserNotFoundException;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins="http://localhost:4200/")
public class UserJpaResource {

	private UserRepository repository;
	
	private PostRepository postRepository;

	public UserJpaResource(UserRepository repository,PostRepository postRepository) {
		this.repository = repository;
		this.postRepository = postRepository;
	}

	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers() {
		return repository.findAll();
	}

	
	@GetMapping("/jpa/users/{id}")
	public Optional<User> retrieveUser(@PathVariable int id) {
		Optional<User> user = repository.findById(id);
		
		if(user==null)
			throw new UserNotFoundException("id:"+id);
		
		return user;
	}

	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {
		repository.deleteById(id);
	}

	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		
		User savedUser = repository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(savedUser.getId())
						.toUri();   
		
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrivePostsForUser(@PathVariable int id) {
		Optional<User> user = repository.findById(id);
		
		if(user.isEmpty())
		{
			throw new UserNotFoundException("id:"+id);
		}
		return user.get().getPosts();
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Post> createPost(@PathVariable int id ,@Valid @RequestBody Post post) {
		Optional<User> user = repository.findById(id);
		
		if(user.isEmpty())
		{
			throw new UserNotFoundException("id:"+id);
		}
		post.setUser(user.get());
		
		Post savedpost = postRepository.save(post);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(savedpost.getId())
						.toUri();   
		
		return ResponseEntity.created(location).build();
	}
}
