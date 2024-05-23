package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.region.RegionCreateDTO;
import org.example.dto.region.RegionDTO;
import org.example.enums.LanguageEnum;
import org.example.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/region")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @PostMapping("/create")
    public ResponseEntity<RegionDTO> create(@Valid @RequestBody RegionCreateDTO createDTO){
        RegionDTO response = regionService.create(createDTO);
        return ResponseEntity.ok().body(response);
    }
    @GetMapping("/all")
    public ResponseEntity<List<RegionDTO>> getAll(){
        return ResponseEntity.ok().body(regionService.getAll());
    }
    @GetMapping("/lang")
    public ResponseEntity<List<RegionDTO>> getAll(@RequestHeader(value = "Accept-Language",defaultValue = "UZ") LanguageEnum lang){
        List<RegionDTO> dtoList = regionService.getAllByLang(lang);
        return ResponseEntity.ok().body(dtoList);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> update(@PathVariable("id") Integer id,@RequestBody RegionCreateDTO createDTO){
        return ResponseEntity.ok().body(regionService.update(id,createDTO));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id){
        return ResponseEntity.ok().body(regionService.delete(id));
    }



}
