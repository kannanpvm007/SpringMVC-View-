/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ast.maven_sample.Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author USER
 */
public class Constants {

	//FTP CONFIGURATION
	public static final String GALLERY_FOLDER_NAME = "gallery";
	public static final String NODIFICATION_FOLDER_NAME = "nodification";
	public static final String base_dir= "C:/Users/ASTLAP10/Thamarai";
	public static final String IMG_PATH="C:/Users/ASTLAP10/Thamarai/images";
	public static final String DOWNLOAD_PATH="C:/Users/ASTLAP10/Thamarai/Download";
	
    public static final String DB_SCRIPTPATH = "scripts/tablescripts.sql";
    public static final DateFormat DATEFORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    public static final String ENCRYPT_ALGORITHM = "PBEWITHMD5ANDDES";

    public static final String PARAM_LIMIT = "limit";
    public static final String PARAM_OFFSET = "offset";
    public static final String PARAM_QUERY = "query";
    public static final String PARAM_SORTING = "sort";

    public static final String USERNAME = "username";
    public static final String SECRET = "password";
    
    public static final String EDIT_TABLE_FLAG_ADD = "ADD";
    public static final String EDIT_TABLE_FLAG_UPDATE = "UPDATE";
    public static final String EDIT_TABLE_FLAG_DELETE = "DELETE";

}
