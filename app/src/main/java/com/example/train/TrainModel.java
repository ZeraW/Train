package com.example.train;

import android.os.Parcel;
import android.os.Parcelable;

public class TrainModel implements Parcelable {
    private String id, trainNo, departureDate, arrivalDate, classA, classB, from, to;

    public TrainModel() {
    }

    public TrainModel(String id, String trainNo, String departureDate, String arrivalDate, String classA, String classB, String from, String to) {
        this.id = id;
        this.trainNo = trainNo;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.classA = classA;
        this.classB = classB;
        this.from = from;
        this.to = to;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getClassA() {
        return classA;
    }

    public void setClassA(String classA) {
        this.classA = classA;
    }

    public String getClassB() {
        return classB;
    }

    public void setClassB(String classB) {
        this.classB = classB;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public static Creator<TrainModel> getCREATOR() {
        return CREATOR;
    }

    protected TrainModel(Parcel in) {
        id = in.readString();
        trainNo = in.readString();
        departureDate = in.readString();
        arrivalDate = in.readString();
        classA = in.readString();
        classB = in.readString();
        from = in.readString();
        to = in.readString();
    }

    public static final Creator<TrainModel> CREATOR = new Creator<TrainModel>() {
        @Override
        public TrainModel createFromParcel(Parcel in) {
            return new TrainModel(in);
        }

        @Override
        public TrainModel[] newArray(int size) {
            return new TrainModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(trainNo);
        parcel.writeString(departureDate);
        parcel.writeString(arrivalDate);
        parcel.writeString(classA);
        parcel.writeString(classB);
        parcel.writeString(from);
        parcel.writeString(to);
    }
}
