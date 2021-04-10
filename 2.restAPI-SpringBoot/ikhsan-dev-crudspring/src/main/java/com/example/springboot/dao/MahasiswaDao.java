package com.example.springboot.dao;

import java.util.List;

import com.example.springboot.entitas.Mahasiswa;

public interface MahasiswaDao {
    public boolean insert(Mahasiswa mahasiswa);

    public boolean update(Mahasiswa mahasiswa);

    public boolean delete(Mahasiswa mahasiswa);

    public Mahasiswa getByNim(String nim);

    public List<Mahasiswa> getAll();
}