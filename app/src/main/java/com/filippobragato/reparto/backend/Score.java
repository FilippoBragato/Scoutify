package com.filippobragato.reparto.backend;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

public class Score {
    private double leader, scouts, role, self, stoker, pioneer, cooker, orienteering, topography, meteorology, signaler, firstAid, artist, expressionist, campism, naturalism, spirit, health;
    private int nLeader, nScouts, nRole, nSelf, nStoker, nPioneer, nCooker, nOrienteering, nTopography, nMeteorology, nSignaler, nFirstAid, nArtist, nExpressionist, nCampism, nNaturalism, nSpirit, nHealth;
    private int diffLeader, diffScouts, diffRole, diffSelf, diffStoker, diffPioneer, diffCooker, diffOrienteering, diffTopography, diffMeteorology, diffSignaler, diffFirstAid, diffArtist, diffExpressionist, diffCampism, diffNaturalism, diffSpirit, diffHealth;

    public Score() {
    }

    public int getDiffLeader() {
        return diffLeader;
    }

    public void setDiffLeader(int diffLeader) {
        this.diffLeader = diffLeader;
    }

    public int getDiffScouts() {
        return diffScouts;
    }

    public void setDiffScouts(int diffScouts) {
        this.diffScouts = diffScouts;
    }

    public int getDiffRole() {
        return diffRole;
    }

    public void setDiffRole(int diffRole) {
        this.diffRole = diffRole;
    }

    public int getDiffSelf() {
        return diffSelf;
    }

    public void setDiffSelf(int diffSelf) {
        this.diffSelf = diffSelf;
    }

    public int getDiffStoker() {
        return diffStoker;
    }

    public void setDiffStoker(int diffStoker) {
        this.diffStoker = diffStoker;
    }

    public int getDiffPioneer() {
        return diffPioneer;
    }

    public void setDiffPioneer(int diffPioneer) {
        this.diffPioneer = diffPioneer;
    }

    public int getDiffCooker() {
        return diffCooker;
    }

    public void setDiffCooker(int diffCooker) {
        this.diffCooker = diffCooker;
    }

    public int getDiffOrienteering() {
        return diffOrienteering;
    }

    public void setDiffOrienteering(int diffOrienteering) {
        this.diffOrienteering = diffOrienteering;
    }

    public int getDiffTopography() {
        return diffTopography;
    }

    public void setDiffTopography(int diffTopography) {
        this.diffTopography = diffTopography;
    }

    public int getDiffMeteorology() {
        return diffMeteorology;
    }

    public void setDiffMeteorology(int diffMeteorology) {
        this.diffMeteorology = diffMeteorology;
    }

    public int getDiffSignaler() {
        return diffSignaler;
    }

    public void setDiffSignaler(int diffSignaler) {
        this.diffSignaler = diffSignaler;
    }

    public int getDiffFirstAid() {
        return diffFirstAid;
    }

    public void setDiffFirstAid(int diffFirstAid) {
        this.diffFirstAid = diffFirstAid;
    }

    public int getDiffArtist() {
        return diffArtist;
    }

    public void setDiffArtist(int diffArtist) {
        this.diffArtist = diffArtist;
    }

    public int getDiffExpressionist() {
        return diffExpressionist;
    }

    public void setDiffExpressionist(int diffExpressionist) {
        this.diffExpressionist = diffExpressionist;
    }

    public int getDiffCampism() {
        return diffCampism;
    }

    public void setDiffCampism(int diffCampism) {
        this.diffCampism = diffCampism;
    }

    public int getDiffNaturalism() {
        return diffNaturalism;
    }

    public void setDiffNaturalism(int diffNaturalism) {
        this.diffNaturalism = diffNaturalism;
    }

    public int getDiffSpirit() {
        return diffSpirit;
    }

    public void setDiffSpirit(int diffSpirit) {
        this.diffSpirit = diffSpirit;
    }

    public int getDiffHealth() {
        return diffHealth;
    }

    public void setDiffHealth(int diffHealth) {
        this.diffHealth = diffHealth;
    }

    public double getLeader() {
        return leader;
    }

    public void setLeader(double leader) {
        this.leader = leader;
    }

    public double getScouts() {
        return scouts;
    }

    public void setScouts(double scouts) {
        this.scouts = scouts;
    }

    public double getRole() {
        return role;
    }

    public void setRole(double role) {
        this.role = role;
    }

    public double getSelf() {
        return self;
    }

    public void setSelf(double self) {
        this.self = self;
    }

    public double getStoker() {
        return stoker;
    }

    public void setStoker(double stoker) {
        this.stoker = stoker;
    }

    public double getPioneer() {
        return pioneer;
    }

    public void setPioneer(double pioneer) {
        this.pioneer = pioneer;
    }

    public double getCooker() {
        return cooker;
    }

    public void setCooker(double cooker) {
        this.cooker = cooker;
    }

    public double getOrienteering() {
        return orienteering;
    }

    public void setOrienteering(double orienteering) {
        this.orienteering = orienteering;
    }

    public double getTopography() {
        return topography;
    }

    public void setTopography(double topography) {
        this.topography = topography;
    }

    public double getMeteorology() {
        return meteorology;
    }

    public void setMeteorology(double meteorology) {
        this.meteorology = meteorology;
    }

    public double getSignaler() {
        return signaler;
    }

    public void setSignaler(double signaler) {
        this.signaler = signaler;
    }

    public double getFirstAid() {
        return firstAid;
    }

    public void setFirstAid(double firstAid) {
        this.firstAid = firstAid;
    }

    public double getArtist() {
        return artist;
    }

    public void setArtist(double artist) {
        this.artist = artist;
    }

    public double getExpressionist() {
        return expressionist;
    }

    public void setExpressionist(double expressionist) {
        this.expressionist = expressionist;
    }

    public double getCampism() {
        return campism;
    }

    public void setCampism(double campism) {
        this.campism = campism;
    }

    public double getNaturalism() {
        return naturalism;
    }

    public void setNaturalism(double naturalism) {
        this.naturalism = naturalism;
    }

    public double getSpirit() {
        return spirit;
    }

    public void setSpirit(double spirit) {
        this.spirit = spirit;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public int getnLeader() {
        return nLeader;
    }

    public void setnLeader(int nLeader) {
        this.nLeader = nLeader;
    }

    public int getnScouts() {
        return nScouts;
    }

    public void setnScouts(int nScouts) {
        this.nScouts = nScouts;
    }

    public int getnRole() {
        return nRole;
    }

    public void setnRole(int nRole) {
        this.nRole = nRole;
    }

    public int getnSelf() {
        return nSelf;
    }

    public void setnSelf(int nSelf) {
        this.nSelf = nSelf;
    }

    public int getnStoker() {
        return nStoker;
    }

    public void setnStoker(int nStoker) {
        this.nStoker = nStoker;
    }

    public int getnPioneer() {
        return nPioneer;
    }

    public void setnPioneer(int nPioneer) {
        this.nPioneer = nPioneer;
    }

    public int getnCooker() {
        return nCooker;
    }

    public void setnCooker(int nCooker) {
        this.nCooker = nCooker;
    }

    public int getnOrienteering() {
        return nOrienteering;
    }

    public void setnOrienteering(int nOrienteering) {
        this.nOrienteering = nOrienteering;
    }

    public int getnTopography() {
        return nTopography;
    }

    public void setnTopography(int nTopography) {
        this.nTopography = nTopography;
    }

    public int getnMeteorology() {
        return nMeteorology;
    }

    public void setnMeteorology(int nMeteorology) {
        this.nMeteorology = nMeteorology;
    }

    public int getnSignaler() {
        return nSignaler;
    }

    public void setnSignaler(int nSignaler) {
        this.nSignaler = nSignaler;
    }

    public int getnFirstAid() {
        return nFirstAid;
    }

    public void setnFirstAid(int nFirstAid) {
        this.nFirstAid = nFirstAid;
    }

    public int getnArtist() {
        return nArtist;
    }

    public void setnArtist(int nArtist) {
        this.nArtist = nArtist;
    }

    public int getnExpressionist() {
        return nExpressionist;
    }

    public void setnExpressionist(int nExpressionist) {
        this.nExpressionist = nExpressionist;
    }

    public int getnCampism() {
        return nCampism;
    }

    public void setnCampism(int nCampism) {
        this.nCampism = nCampism;
    }

    public int getnNaturalism() {
        return nNaturalism;
    }

    public void setnNaturalism(int nNaturalism) {
        this.nNaturalism = nNaturalism;
    }

    public int getnSpirit() {
        return nSpirit;
    }

    public void setnSpirit(int nSpirit) {
        this.nSpirit = nSpirit;
    }

    public int getnHealth() {
        return nHealth;
    }

    public void setnHealth(int nHealth) {
        this.nHealth = nHealth;
    }

    public double[] meanAsArray(){
        return new double[]{leader, scouts, role, self, stoker, pioneer, cooker, orienteering, topography, meteorology, signaler, firstAid, artist, expressionist, campism, naturalism, spirit, health};
    }

    public int[] numAsArray(){
        return new int[]{nLeader, nScouts, nRole, nSelf, nStoker, nPioneer, nCooker, nOrienteering, nTopography, nMeteorology, nSignaler, nFirstAid, nArtist, nExpressionist, nCampism, nNaturalism, nSpirit, nHealth};
    }

    public int[] diffAsArray(){
        return new int[]{diffLeader, diffScouts, diffRole, diffSelf, diffStoker, diffPioneer, diffCooker, diffOrienteering, diffTopography, diffMeteorology, diffSignaler, diffFirstAid, diffArtist, diffExpressionist, diffCampism, diffNaturalism, diffSpirit, diffHealth};
    }

    public void applyMeanAsArray(double[] mean){
        this.leader = mean[0];
        this.scouts = mean[1];
        this.role= mean[2];
        this.self= mean[3];
        this.stoker= mean[4];
        this.pioneer= mean[5];
        this.cooker= mean[6];
        this.orienteering= mean[7];
        this.topography= mean[8];
        this.meteorology= mean[9];
        this.signaler= mean[10];
        this.firstAid= mean[11];
        this.artist= mean[12];
        this.expressionist= mean[13];
        this.campism= mean[14];
        this.naturalism= mean[15];
        this.spirit= mean[16];
        this.health= mean[17];
    }
    public void applyNumAsArray(int[] num){
        this.nLeader = num[0];
        this.nScouts = num[1];
        this.nRole= num[2];
        this.nSelf= num[3];
        this.nStoker= num[4];
        this.nPioneer= num[5];
        this.nCooker= num[6];
        this.nOrienteering= num[7];
        this.nTopography= num[8];
        this.nMeteorology= num[9];
        this.nSignaler= num[10];
        this.nFirstAid= num[11];
        this.nArtist= num[12];
        this.nExpressionist= num[13];
        this.nCampism= num[14];
        this.nNaturalism= num[15];
        this.nSpirit= num[16];
        this.nHealth= num[17];
    }
    public void applyDiffAsArray(int[] diff){
        this.diffLeader = diff[0];
        this.diffScouts = diff[1];
        this.diffRole= diff[2];
        this.diffSelf= diff[3];
        this.diffStoker= diff[4];
        this.diffPioneer= diff[5];
        this.diffCooker= diff[6];
        this.diffOrienteering= diff[7];
        this.diffTopography= diff[8];
        this.diffMeteorology= diff[9];
        this.diffSignaler= diff[10];
        this.diffFirstAid= diff[11];
        this.diffArtist= diff[12];
        this.diffExpressionist= diff[13];
        this.diffCampism= diff[14];
        this.diffNaturalism= diff[15];
        this.diffSpirit= diff[16];
        this.diffHealth= diff[17];
    }
}
