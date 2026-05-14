const fs = require('fs');
const html = fs.readFileSync('tests.html', 'utf8');
const scriptMatch = html.match(/<script>([\s\S]*?)<\/script>/);
const code = scriptMatch[1];

// Remove DOM-dependent renderResults call and add console reporter
const modified = code.replace('renderResults();', `
const total = results.length;
const passed = results.filter(r => r.pass).length;
const failed = results.filter(r => !r.pass);
console.log(passed + '/' + total + ' tests passed');
if (failed.length) {
    failed.forEach(f => console.log('FAIL: [' + f.suite + '] ' + f.name + ' -- ' + f.error));
    process.exit(1);
} else {
    console.log('ALL PASS');
}
`);

// Remove DOM references
const final = modified.replace("const container = document.getElementById('results');", "return;");

eval(final);
