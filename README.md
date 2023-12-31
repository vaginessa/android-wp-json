# AndroidWpJSON

WordPress has an extensive API called the REST API, which is accessible through the wp-json endpoint. Using this API, you can access and manipulate WordPress data, such as posts, pages, and user information, in a programmatic way. This allows you to integrate WordPress with other systems or build custom applications that interact with WordPress data without the need for a webview. It's a powerful tool that can save you a lot of development time and effort!

## Showcase

[![creatorbe on YouTube](https://i.ytimg.com/vi/rXyFc4fKaUg/maxresdefault.jpg))](https://youtube.com/shorts/rXyFc4fKaUg "Android WP JSON")

You can test app easily by click the download button below and install it on your android smartphone.

<!-- BEGIN LATEST DOWNLOAD BUTTON -->
**APK DEBUG**

[![Download apk](https://custom-icon-badges.demolab.com/badge/-Download-blue?style=for-the-badge&logo=download&logoColor=white "Download apk")](https://raw.githubusercontent.com/CreatorB/android-wp-json/main/showcase/app-debug.apk)
<!-- END LATEST DOWNLOAD BUTTON -->

## Getting Started

This project is a starting point for how to implementation wp-json in your application.

A few resources to get you started's:

- [WordPress REST API Handbook](https://developer.wordpress.org/rest-api/reference/posts/)

## Features

- Fetch category `/wp-json/wp/v2/categories` as left drawer menu
- Posts list `/wp-json/wp/v2/posts` with filter search and pagination load more on scroll
- Thumbnails `/wp-json/wp/v2/media/{mediaId}` pre fetch featured media into posts list

Detail API used of this app, you can check at [Retrofit Interface](https://github.com/CreatorB/android-wp-json/blob/main/app/src/main/java/id/or/siber/interfaces/ApiService.kt) file.

## Contribution

This is a very simple Showcase and you're welcome to contribute.

1. Fork it.
2. Clone (`git clone git@github.com:CreatorB/android-wp-json.git`) 
3. [Optional: keep your fork uptodate] Add this repo as a remote (`git remote add upstream git@github.com:CreatorB/android-wp-json.git`)
4. Create your feature branch (`git checkout -b my-new-feature`)
5. Add your changes (`git add .`)
6. Commit your changes (`git commit -m 'Add a new feature that does X'`)
7. [Optional] Keep your branch uptodate to avoid merge conflicts later (`git fetch upstream && git rebase upstream/main`)
8. Push to the branch (`git push origin my-new-feature`)
9. Create a Pull Request (PR): Go to the original repository on GitHub. You should see a prompt to create a pull request from your new branch. You can also go to the "Pull requests" tab and click on "New pull request". Select your feature branch and make sure the base repository and branch are correct.
10. Describe Your Changes: Fill in the pull request template with all the relevant information, explaining the changes you've made and the value they add to the project.
11. Submit the Pull Request: Once you've filled out the template, submit the pull request.

Any questions? let me know at [issues](https://github.com/CreatorB/android-wp-json/issues)

_سُبْحَانَكَ اللَّهُمَّ وَبِحَمْدِكَ، أَشْهَدُ أَنْ لَا إِلَهَ إِلَّا أَنْتَ، أَسْتَغْفِرُكَ وَأَتُوبُ إِلَيْكَ_