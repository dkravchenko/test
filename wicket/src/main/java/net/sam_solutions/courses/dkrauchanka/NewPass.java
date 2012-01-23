package net.sam_solutions.courses.dkrauchanka;

public class NewPass {
    private String pass;
    private String newPass;
    private String confirmPass;
    
    public NewPass(){
        
    }

    public String getConfirmPass() {
        return confirmPass;
    }

    public void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NewPass other = (NewPass) obj;
        if ((this.pass == null) ? (other.pass != null) : !this.pass.equals(other.pass)) {
            return false;
        }
        if ((this.newPass == null) ? (other.newPass != null) : !this.newPass.equals(other.newPass)) {
            return false;
        }
        if ((this.confirmPass == null) ? (other.confirmPass != null) : !this.confirmPass.equals(other.confirmPass)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + (this.pass != null ? this.pass.hashCode() : 0);
        hash = 79 * hash + (this.newPass != null ? this.newPass.hashCode() : 0);
        hash = 79 * hash + (this.confirmPass != null ? this.confirmPass.hashCode() : 0);
        return hash;
    }
    
    
}
