package dev.patika.vet_management.api;

import dev.patika.vet_management.business.abstracts.IAnimalService;
import dev.patika.vet_management.core.result.Result;
import dev.patika.vet_management.core.result.ResultData;
import dev.patika.vet_management.core.utilies.ResultHelper;
import dev.patika.vet_management.dto.request.animal.AnimalSaveRequest;
import dev.patika.vet_management.dto.request.animal.AnimalUpdateRequest;
import dev.patika.vet_management.dto.response.CursorResponse;
import dev.patika.vet_management.dto.response.animal.AnimalResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/animals")
@RequiredArgsConstructor
public class AnimalContorller {
    private final IAnimalService animalService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AnimalResponse> create(@Valid @RequestBody AnimalSaveRequest animalSaveRequest) {
        return this.animalService.create(animalSaveRequest);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> get(@PathVariable("id") long id)
    {
        return this.animalService.find(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AnimalResponse>> cursor(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        Page<AnimalResponse> animalResponsePage =this.animalService.cursor(page, pageSize);
        return ResultHelper.cursor(animalResponsePage);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") long id) {return this.animalService.delete(id);}

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> update(@Valid @RequestBody AnimalUpdateRequest animalUpdateRequest){
        return this.animalService.update(animalUpdateRequest);
    }

    @GetMapping("/name/{name}")
    public ResultData<List<AnimalResponse>> findByName(@PathVariable("name") String name) {
        return this.animalService.findByName(name);
    }

    @GetMapping("/customer/{name}")
    public ResultData<List<AnimalResponse>> findByCustomerName(@PathVariable("name") String name) {
        return this.animalService.findByCustomerName(name);
    }
}
