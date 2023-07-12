package com.isteer.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.isteer.module.ImageModule;

@Repository
public class ImageDaoImpl implements ImageDao {

	private final String INSERT_IMAGE = "INSERT INTO IMAGEDATA (IMAGEDATA,IMAGEUUID) VALUES(?,?)";
	private final String GET_IMAGE = "SELECT IMAGEDATA FROM IMAGEDATA WHERE IMAGEUUID=?";
	private final String GET_ALL_IMAGES = "SELECT IMAGEUUID FROM IMAGEDATA";

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void addImageToDB(List<ImageModule> imageModules) {

		jdbcTemplate.batchUpdate(INSERT_IMAGE,new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setBytes(1,imageModules.get(i).getImageData());
				ps.setString(2, imageModules.get(i).getUUID());
				
			}
			
			@Override
			public int getBatchSize() {
				
				return imageModules.size();
			}
		});
		
	}

	@Override
	public byte[] getImageFromDB(String imageUUID) {

		return jdbcTemplate.query(GET_IMAGE, new ResultSetExtractor<byte[]>() {

			@Override
			public byte[] extractData(ResultSet rs) throws SQLException, DataAccessException {
				byte[] data = null;
				while (rs.next()) {
					data = rs.getBytes("IMAGEDATA");
				}
				return data;
			}

		},imageUUID);

	}

	@Override
	public List<String> getAllImagesFromDB() {

		return jdbcTemplate.query(GET_ALL_IMAGES, rs -> {

			List<String> allImageUUID = new ArrayList<String>();
			while (rs.next()) {
				allImageUUID.add(rs.getString("IMAGEUUID"));
			}
			return allImageUUID;

		});

	}

}
