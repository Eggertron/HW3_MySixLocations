package com.example.eggertron.hw3_mysixlocations;

import android.graphics.drawable.Drawable;

/**
 * Model of location data
 * Created by Edgar Han on 10/29/16.
 */
public class Locations {

    String[] names = {
            "Burruss Hall",
            "Lane Stadium",
            "Moss Art Center",
            "Duck Pond",
            "Goodwin Hall",
            "The Inn at Virginia Tech"
    };

    double[] latitudes = {
            37.228355,
            37.219462,
            37.231849,
            37.2257837,
            37.2324426,
            37.2298222
    };

    double[] longitudes = {
            -80.423065,
            -80.418023,
            -80.418066,
            -80.4283172,
            -80.4256846,
            -80.4298353
    };

    String[] mp3s = {
            "burruss.mp3",
            "lane.mp3",
            "moss.mp3",
            null,
            null,
            null
    };

    String[] descriptions = {
            "Burruss Hall is the main administration building on campus. It contains a 3,003-seat auditorium, a venue where major events such as commencement, presidential speeches, concerts, and arts shows are held. \n" +
                    "\n" +
                    "Additionally, the building houses interior design and landscape architecture offices, studios,and classrooms for the College of Architecture and Urban Studies. ",
            "Billed as the toughest place in college football for opponents to play by Rivals.com, Lane Stadium seats 66,233. Lane Stadium/Worsham Field has gone through numerous changes, renovations and additions. But through it all, it has always been regarded as one of the finest places to watch – and toughest places for opponents to play – a college football game.",
            "The Center for the Arts at Virginia Tech and the Institute for Creativity, Arts, and Technology are headquartered in the Moss Arts Center. The 150,000-square-foot facility includes a 1,260-seat performance hall, visual arts galleries, amphitheater, four-story experimental Cube, and multiple studios. The center also offers a multimedia studio, production control room, newsroom, and associated classroom in support of the Department of Communication.",
            "The Duck Pond is a place secluded in nature, where people can go to reflect, mediate, and relax. Going to this place helps to focus on things that matter in life. It is easy to get lost in the details of life, but this place helps to focus on the big picture. It is located on Duck Pond Drive, so it is technically still on the Virginia Tech campus, but it feels as if it is off of campus. This place has a convenient location for residents, while also having a unique location to interest visitors of Blacksburg. This pond is known for its romantic date spot. There is a myth at Virginia Tech that if you kiss your significant other here, then you are destined to marry him or her.",
            "Goodwin Hall is the flagship building for the College of Engineering. It houses 40 instructional and research labs, eight classrooms, the Quillen Family Auditorium, and 150 offices for several engineering departments.",
            "As the largest hotel and conference center in Blacksburg, Va., the Inn at Virginia Tech and Skelton Conference Center offers nearly 24,000 square feet of conference space, including a 700-seat ballroom, 10 conference rooms, and 147 hotel rooms and suites. "
    };

    int[] maps =  {
            R.drawable.mapburruss,
            R.drawable.maplane,
            R.drawable.mapmoss,
            R.drawable.mapduck,
            R.drawable.mapgoodwin,
            R.drawable.mapinn
    };

    int[] images = {
            R.drawable.imgburruss,
            R.drawable.imglane,
            R.drawable.imgmoss,
            R.drawable.imgduck,
            R.drawable.imggoodwin,
            R.drawable.imginn
    };
}
