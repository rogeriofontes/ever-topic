package br.com.everis.controllers;

import br.com.everis.model.domain.Posts;
import br.com.everis.model.repositories.PostRepository;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;
import jakarta.inject.Inject;

import io.micronaut.core.version.annotation.Version;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@Controller("/posts")
public class PostController {

    @Inject
    private PostRepository postRepository;

   /* Injecao por construtor
   public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }*/

    @Post
    public HttpResponse<Posts> add(@Body @Valid Posts post){
        Posts save = postRepository.save(post);
        return HttpResponse.status(HttpStatus.CREATED).body(save);
    }

    @Version("1")
    @Get
    public HttpResponse<List<Posts>> get(){
         return HttpResponse.status(HttpStatus.OK).body(getPosts());
    }

   /* @Version("2")
    @Get("{?max,offset}")
    public HttpResponse<List<Posts>> getV2(@Nullable Integer max, @Nullable Integer offset){
        List<Posts> posts = getPosts().stream()
                .skip(offset == null ? 0 : offset)
                .limit(max == null ? 10000 : max)
                .collect(Collectors.toList());

        return HttpResponse.status(HttpStatus.OK).body(posts);
    }*/

    private List<Posts> getPosts() {
        Iterable<Posts> allPost = postRepository.findAll();
        List<Posts> postList = new ArrayList<>();
        allPost.iterator().forEachRemaining(postList::add);
        return postList;
    }
}
