package dev.alexcoss.dao;

import dev.alexcoss.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM person", new PersonMapper());
    }

    public Person getPerson(int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?", new Object[]{id}, new PersonMapper())
                .stream()
                .findAny()
                .orElse(null);
    }

    public Optional<Person> getPerson(String email) {
        return jdbcTemplate.query("SELECT * FROM person WHERE email=?", new Object[]{email}, new PersonMapper())
                .stream()
                .findAny();
    }

    public void addPerson(Person person) {
        jdbcTemplate.update("INSERT INTO person VALUES (?, ?, ?, ?, ?)", person.getName(), person.getAge(),
                person.getEmail(), person.getAddress(), person.isAdmin());
    }

    public void updatePerson(int id, Person updatePerson) {
        jdbcTemplate.update("UPDATE person SET name = ?, age = ?, email = ?, address = ?, is_admin = ? WHERE id=?", updatePerson.getName(), updatePerson.getAge(),
                updatePerson.getEmail(), updatePerson.getAddress(), updatePerson.isAdmin(), id);
    }

    public void deletePerson(int id) {
        jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
    }

    public void addPeople(List<Person> people) {
        jdbcTemplate.batchUpdate("INSERT INTO person VALUES (?, ?, ?, ?, ?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, people.get(i).getName());
                ps.setInt(2, people.get(i).getAge());
                ps.setString(3, people.get(i).getEmail());
                ps.setString(4, people.get(i).getAddress());
                ps.setBoolean(5, people.get(i).isAdmin());
            }

            @Override
            public int getBatchSize() {
                return people.size();
            }
        });
    }
}
