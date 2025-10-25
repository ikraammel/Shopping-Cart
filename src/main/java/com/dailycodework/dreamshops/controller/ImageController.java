package com.dailycodework.dreamshops.controller;

import com.dailycodework.dreamshops.exceptions.ResourceNotFoundException;
import com.dailycodework.dreamshops.dto.ImageDto;
import com.dailycodework.dreamshops.response.ApiResponse;
import com.dailycodework.dreamshops.model.Image;
import com.dailycodework.dreamshops.service.image.IImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/images")
public class ImageController {
    @Autowired
    private final IImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse> saveImages(@RequestParam List<MultipartFile> files, @RequestParam Long productId){
        try{
            List<ImageDto> imageDtos = imageService.saveImages(files,productId);
            return ResponseEntity.ok(new ApiResponse("Upload success!",imageDtos));
        }catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Upload failed!",e.getMessage()));
        }
    }
    @GetMapping("/image/download/{imageId}")
    public ResponseEntity<Resource> downloadImage(@PathVariable Long imageId) {
        try {
            Image image = imageService.getImageById(imageId);
            if (image == null || image.getImage() == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            byte[] data = image.getImage();

            ByteArrayResource resource = new ByteArrayResource(data);

            String fileType = image.getFileType() != null ? image.getFileType() : "application/octet-stream";
            String fileName = image.getFileName() != null ? image.getFileName() : "file";

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(fileType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                    .body(resource);

        }catch (Exception e) {
            e.printStackTrace(); // Ajoute ça pour voir l'erreur dans la console
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ByteArrayResource(("Erreur: " + e.getMessage()).getBytes()));
        }

    }

    @PutMapping("/image/{imageId}/update")
    public ResponseEntity<ApiResponse> updateImage(@PathVariable Long imageId,@RequestParam MultipartFile file){
        try{
            Image image = imageService.getImageById(imageId);
            if(image !=null){
                imageService.updateImage(file, imageId);
                return ResponseEntity.ok(new ApiResponse("Update success!",null));
            }
        } catch (ResourceNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Update failed",INTERNAL_SERVER_ERROR));
    }


    @DeleteMapping("image/{imageId}/delete")
    public ResponseEntity<ApiResponse> deleteImage(@PathVariable Long imageId){
    try{
        Image image = imageService.getImageById(imageId);
        if(image!=null){
            imageService.deleteImageById(imageId);
            return ResponseEntity.ok(new ApiResponse("Delete success!",null));
        }
    } catch(ResourceNotFoundException e){
        return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
    }
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Delete failed",INTERNAL_SERVER_ERROR));
}
}


