/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.entity.Album;
import dao.entity.Picture;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.primefaces.util.Base64;
import service.AlbumServiceLocal;
import service.PictureServiceLocal;
import utils.SessionUtils;

/**
 *
 * @author Denis
 */
@ManagedBean(name = "AlbumMB")
@SessionScoped
public class AlbumMB {
    private String name;
    private UploadedFile pictureFile;
    private StreamedContent readablePicture;
    private String albumId;
    private Integer userId;
    

    @EJB
    AlbumServiceLocal albumService;
    
    @EJB
    PictureServiceLocal pictureService;

    public AlbumMB() {

    }
    
    public void init(){
        this.userId = null;
        this.albumId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("albumId");
        
        try{
            this.userId = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("wallId"));
        }catch(NumberFormatException nfe){
            this.userId = (Integer) SessionUtils.getItem(SessionUtils.ID_KEY);
        }
    }
    
    
    public List<Album> getAlbums(){
        return albumService.getAlbums(this.userId);
    }
    
    public void createAlbum(){
        albumService.createAlbum(name, this.userId);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void upload(){
        String idAlbum = this.albumId;
        
        try {
            FacesMessage msg = new FacesMessage("Success! ", pictureFile.getFileName() + " is uploaded.");
            
            FacesContext.getCurrentInstance().addMessage(null, msg);
            Logger.getLogger(ProfileMB.class.getName()).log(Level.SEVERE, "DEBUG DEBUG DEBUG : "+pictureFile.getFileName() + " - "+pictureFile.getInputstream() + " - "+pictureFile.getSize());
           
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = pictureFile.getInputstream();
            byte[] buffer = new byte[1024];
            while (true) {
                int r = in.read(buffer);
                if (r == -1) break;
                out.write(buffer, 0, r);
            }
            Integer id = Integer.parseInt(idAlbum);
            pictureService.createPicture(id, out.toByteArray());
        } catch (IOException ex) {
            Logger.getLogger(ProfileMB.class.getName()).log(Level.SEVERE, null, ex);
        }
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        try {
            context.redirect(context.getRequestContextPath() + "/faces/gallery.xhtml?albumId="+idAlbum);
        } catch (IOException ex) {
            Logger.getLogger(AlbumMB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public UploadedFile getPictureFile() {
        return pictureFile;
    }

    public void setPictureFile(UploadedFile pictureFile) {
        this.pictureFile = pictureFile;
    }

    public StreamedContent getReadablePicture() {
        return readablePicture;
    }

    public void setReadablePicture(StreamedContent readablePicture) {
        this.readablePicture = readablePicture;
    }
    
    public List<String> getPictures(){
        List<String> pictures = new ArrayList<>();
        Album a = albumService.viewAlbum(Integer.parseInt(this.albumId), this.userId);
        if(a != null){
            if(a.getPictures() != null){
                for (Picture p : a.getPictures()) {
                    pictures.add(Base64.encodeToString(p.getContent(),false));
                }
            }
        }
        return pictures;        
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    
  
}
