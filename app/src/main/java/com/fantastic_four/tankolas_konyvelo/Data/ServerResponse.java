package com.fantastic_four.tankolas_konyvelo.Data;

import com.fantastic_four.tankolas_konyvelo.Car;
import com.fantastic_four.tankolas_konyvelo.PersonalChalk;

import java.util.List;

public class ServerResponse {

    private Car car;
    private List<PersonalChalk> chalks;

    public ServerResponse(Car car, List<PersonalChalk> chalks) {
        this.car = car;
        this.chalks = chalks;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<PersonalChalk> getChalks() {
        return chalks;
    }

    public void setChalkList(List<PersonalChalk> chalks) {
        this.chalks = chalks;
    }
}
