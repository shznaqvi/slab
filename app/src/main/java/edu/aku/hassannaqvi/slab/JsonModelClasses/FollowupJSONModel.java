package edu.aku.hassannaqvi.slab.JsonModelClasses;

/**
 * Created by ramsha.ahmed on 4/12/2018.
 */

public class FollowupJSONModel {

        private String uuid = "";
        private String childName = "";

        public String getUuid() {
                return uuid;
        }

        public void setUuid(String uuid) {
                this.uuid = uuid;
        }

        public String getChildName() {
                return childName;
        }

        public void setChildName(String childName) {
                this.childName = childName;
        }

        public String getSfu01() {
                return sfu01;
        }

        public void setSfu01(String sfu01) {
                this.sfu01 = sfu01;
        }

        private String sfu01 = "";

}
