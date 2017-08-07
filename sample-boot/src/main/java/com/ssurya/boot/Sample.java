/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ssurya.boot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

/**
 *
 * @author ssuryade
 */
public class Sample {
    
    public static void main(String[] args) {
        System.out.println("Test");
        
        Sample s = new Sample();
        s.solution("photo.jpg, Warsaw, 2013-09-05 14:08:15\\njohn.png, London, 2015-06-20 15:13:22\\nmyFriends.png, Warsaw, 2013-09-05 14:07:13\\nEiffel.jpg, Paris, 2015-07-23 08:03:02\\npisatower.jpg, Paris, 2015-07-22 23:59:59\\nBOB.jpg, London, 2015-08-05 00:02:03\\nnotredame.png, Paris, 2015-09-01 12:00:00\\nme.jpg, Warsaw, 2013-09-06 15:40:22\\na.png, Warsaw, 2016-02-13 13:33:50\\nb.jpg, Warsaw, 2016-01-02 15:12:22\\nc.jpg, Warsaw, 2016-01-02 14:34:30\\nd.jpg, Warsaw, 2016-01-02 15:15:01\\ne.png, Warsaw, 2016-01-02 09:49:09\\nf.png, Warsaw, 2016-01-02 10:55:32\\ng.jpg, Warsaw, 2016-02-29 22:13:11");
    }
    
    
    
    public String solution(String S) {
        StringTokenizer st = new StringTokenizer(S, "\n");
        List<Photo> photos = new ArrayList<>();
        
        while(st.hasMoreElements()) {
            Photo photo = new Photo((String) st.nextElement());
            System.out.println(photo.toString());
           
        }
        //Group by city name
        Map<String, List<Photo>> groupPhotos = new HashMap<>();
        
        for (List<Photo> photos1 : groupPhotos.values()) {
            Collections.sort(photos1, new Comparator<Photo>() {
            public int compare(Photo result1, Photo result2) {
                return result1.getDateTaken().compareTo(result2.getDateTaken());
            }
         });
        }
        
        
        
        return S;
    }
    
    private static class Photo {
        private String actualName;
        private String city;
        private String photoName;
        private Date dateTaken;
        private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     
        public Photo(String actualName) {
             this.actualName = actualName; 
             String[] nameParams = actualName.split(",");
            this.city = nameParams[1];
            this.photoName = nameParams[0];
            try {this.dateTaken = format.parse(nameParams[2]);}
            catch(ParseException e){
                e.printStackTrace();
            }
        }   
        
        
        //public 

        public String getActualName() {
            return actualName;
        }

        public void setActualName(String actualName) {
            this.actualName = actualName;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getPhotoName() {
            return photoName;
        }

        public void setPhotoName(String photoName) {
            this.photoName = photoName;
        }

        public Date getDateTaken() {
            return dateTaken;
        }

        public void setDateTaken(Date dateTaken) {
            this.dateTaken = dateTaken;
        }

        public SimpleDateFormat getFormat() {
            return format;
        }

        public void setFormat(SimpleDateFormat format) {
            this.format = format;
        }

        @Override
        public String toString() {
            return "Photo{" + "actualName=" + actualName + ", city=" + city + ", photoName=" + photoName + ", dateTaken=" + dateTaken + '}';
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 43 * hash + Objects.hashCode(this.actualName);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Photo other = (Photo) obj;
            if (!Objects.equals(this.actualName, other.actualName)) {
                return false;
            }
           
            return true;
        }
        
        
    }

}
