package com.example.springboot.dao;

import com.example.springboot.entitas.Mahasiswa;
import com.sinaungoding.crud.jdbc.dao.MahasiswaDao;
import com.sinaungoding.crud.jdbc.dao.MahasiswaRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Repository
public class MahasiswaRepository implements MahasiswaDao {

    private final String INSERT = "INSERT INTO mahasiswa (nim, nama, ipk, jurusan) "
            + "	VALUES (?,?,?,?)";
    private final String UPDATE = "UPDATE mahasiswa SET nama=?, ipk=?, jurusan=? WHERE nim=?";
    private final String DELETE = "DELETE FROM mahasiswa WHERE nim=?";
    private final String SELECT_ALL = "SELECT nim,nama,ipk,jurusan FROM mahasiswa";
    private final String SELECT_BY_NIM = "SELECT nim,nama,ipk,jurusan FROM mahasiswa WHERE nim=?";

    private static Logger LOGGER = LoggerFactory.getLogger(MahasiswaRepository.class.getName());

    @Autowired
    private DataSource dataSource;

    private Connection connection;
    private PreparedStatement preparedStatement;

    @Override
    public boolean insert(Mahasiswa mahasiswa) {
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, mahasiswa.getNim());
            preparedStatement.setString(2, mahasiswa.getNama());
            preparedStatement.setFloat(3, mahasiswa.getIpk());
            preparedStatement.setString(4, mahasiswa.getJurusan());
            return preparedStatement.executeUpdate() > 0 ? true : false;
        } catch (SQLException e) {
            LOGGER.error(null, e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    LOGGER.error(null, e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error(null, e);
                }
            }

        }
        return false;
    }

    @Override
    public boolean update(Mahasiswa mahasiswa) {
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, mahasiswa.getNama());
            preparedStatement.setFloat(2, mahasiswa.getIpk());
            preparedStatement.setString(3, mahasiswa.getJurusan());
            preparedStatement.setString(4, mahasiswa.getNim());
            return preparedStatement.executeUpdate() > 0 ? true : false;
        } catch (SQLException e) {
            LOGGER.error(null, e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    LOGGER.error(null, e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error(null, e);
                }
            }
        }
        return false;
    }

    @Override
    public boolean delete(Mahasiswa mahasiswa) {
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setString(1, mahasiswa.getNim());
            return preparedStatement.executeUpdate() > 0 ? true : false;
        } catch (SQLException e) {
            LOGGER.error(null, e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    LOGGER.error(null, e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error(null, e);
                }
            }

        }
        return false;
    }

    @Override
    public Mahasiswa getByNim(String nim) {
        ResultSet resultSet = null;
        try {
            Mahasiswa mahasiswa = new Mahasiswa();
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_BY_NIM);
            preparedStatement.setString(1, nim);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                mahasiswa.setIpk(resultSet.getFloat("ipk"));
                mahasiswa.setJurusan(resultSet.getString("jurusan"));
                mahasiswa.setNama(resultSet.getString("nama"));
                mahasiswa.setNim(resultSet.getString("nim"));
            }
            return mahasiswa;
        } catch (SQLException e) {
            LOGGER.error(null, e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    LOGGER.error(null, e);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    LOGGER.error(null, e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error(null, e);
                }
            }

        }
        return null;
    }

    @Override
    public List<Mahasiswa> getAll() {
        ResultSet resultSet = null;
        try {
            List<Mahasiswa> mahasiswas = new ArrayList<>();
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setIpk(resultSet.getFloat("ipk"));
                mahasiswa.setJurusan(resultSet.getString("jurusan"));
                mahasiswa.setNama(resultSet.getString("nama"));
                mahasiswa.setNim(resultSet.getString("nim"));
            }
            return mahasiswas;
        } catch (SQLException e) {
            LOGGER.error(null, e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    LOGGER.error(null, e);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    LOGGER.error(null, e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LOGGER.error(null, e);
                }
            }

        }
        return null;
    }
}

