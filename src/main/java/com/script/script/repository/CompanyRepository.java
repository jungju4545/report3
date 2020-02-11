package com.script.script.repository;

import java.util.List;

import com.script.script.model.Company;

public interface CompanyRepository {
void save(Company c);
List<Company>findAll();
void delete();
}
