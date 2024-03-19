package com.ieening.learnSerializable;

import java.util.Map;

/**
 * ComplexStudent
 */
public class ComplexStudent {

    String name;
    int age;
    Map<String, Double> scores;
    ContactInfo ContactInfo;

    public ComplexStudent() {
    }

    public ComplexStudent(String name, int age, Map<String, Double> scores,
            com.ieening.learnSerializable.ComplexStudent.ContactInfo contactInfo) {
        this.name = name;
        this.age = age;
        this.scores = scores;
        ContactInfo = contactInfo;
    }

    public static class ContactInfo {
        String phone;
        String address;
        String email;

        public ContactInfo() {
        }

        public ContactInfo(String phone, String address, String email) {
            this.phone = phone;
            this.address = address;
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((phone == null) ? 0 : phone.hashCode());
            result = prime * result + ((address == null) ? 0 : address.hashCode());
            result = prime * result + ((email == null) ? 0 : email.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            ContactInfo other = (ContactInfo) obj;
            if (phone == null) {
                if (other.phone != null)
                    return false;
            } else if (!phone.equals(other.phone))
                return false;
            if (address == null) {
                if (other.address != null)
                    return false;
            } else if (!address.equals(other.address))
                return false;
            if (email == null) {
                if (other.email != null)
                    return false;
            } else if (!email.equals(other.email))
                return false;
            return true;
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Map<String, Double> getScores() {
        return scores;
    }

    public void setScores(Map<String, Double> scores) {
        this.scores = scores;
    }

    public ContactInfo getContactInfo() {
        return ContactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        ContactInfo = contactInfo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + age;
        result = prime * result + ((scores == null) ? 0 : scores.hashCode());
        result = prime * result + ((ContactInfo == null) ? 0 : ContactInfo.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ComplexStudent other = (ComplexStudent) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (age != other.age)
            return false;
        if (scores == null) {
            if (other.scores != null)
                return false;
        } else if (!scores.equals(other.scores))
            return false;
        if (ContactInfo == null) {
            if (other.ContactInfo != null)
                return false;
        } else if (!ContactInfo.equals(other.ContactInfo))
            return false;
        return true;
    }
}