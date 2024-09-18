package org.eduaid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediaService {

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private VolunteerRepository volunteerRepository;

    // Save media file to the database
    public void saveFile(Media media) {
        mediaRepository.save(media);
    }

    // Get all media files
    public List<Media> getAllFiles() {
        return mediaRepository.findAll();
    }

    // Get media by ID
    public Media getFileById(Long id) {
        return mediaRepository.findById(id).orElse(null);
    }

    // Get media files by user email
    public List<Media> getMediaByUserEmail(String userEmail) {
        return mediaRepository.findByUserEmail(userEmail);
    }



    // Delete media by ID
    public void deleteFileById(Long id) {
        mediaRepository.deleteById(id);
    }
    
    
 



}
