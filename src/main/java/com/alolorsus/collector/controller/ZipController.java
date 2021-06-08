package com.alolorsus.collector.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.alolorsus.collector.entity.Album;
import com.alolorsus.collector.entity.Carta;
import com.alolorsus.collector.service.AlbumService;
import com.alolorsus.util.ScryfallService;

@RestController
@RequestMapping("/collector")
@CrossOrigin(origins = { "*" })
public class ZipController {
	
	@Autowired
	private AlbumService albumService;
	
	@GetMapping("/album/zip/{id}")
	public ResponseEntity<StreamingResponseBody> createZip (@PathVariable Integer id) {
		
		Album album = albumService.getAlbum(id);
		
		List<String> url_list = new ArrayList<String>();
		for (Carta carta : album.getCartas()) {
			Map<String, String> imgs = ScryfallService.getImagenesCarta(carta.getScryfallId());
			url_list.add(imgs.get("large"));
		}
		
		return ResponseEntity.ok().header("Content-Disposition", "attachment; filename=\"album.zip\"").body(
			out -> {
			var zipOutputStream = new ZipOutputStream(out);
			
			for (int i=0; i < url_list.size(); i++) {
				
				byte[] img = ScryfallService.getImagen(url_list.get(i));
				ZipEntry zipfile = new ZipEntry("carta_" + String.valueOf(i) + ".jpg");
				zipfile.setSize(img.length);
				
				zipOutputStream.putNextEntry(zipfile);
				zipOutputStream.write(img);

                zipOutputStream.closeEntry();
            }

            zipOutputStream.close();
			
		});
	}
	
}
